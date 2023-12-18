package ru.job4j.dsl

import org.apache.commons.dbcp2.BasicDataSource
import ru.job4j.oop.tracker.Item
import ru.job4j.oop.tracker.Store
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.Statement

class JdbcItemStore :
    Store,
    AutoCloseable {
    private lateinit var ds: BasicDataSource

    fun init(ds: BasicDataSource) {
        this.ds = ds
        initTable()
    }

    private fun initTable() {
        ds.execute {
            val statement = it.createStatement()
            statement.executeUpdate(
                """
                    CREATE TABLE IF NOT EXISTS items (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR,
                        created TIMESTAMP
                    );
                    """,
            )
        }
    }

    override fun add(item: Item): Item {
        ds.execute {
            val st =
                it.prepareStatement(
                    "insert into items(name, created) values(?, ?)",
                    Statement.RETURN_GENERATED_KEYS,
                )
            st.setString(1, item.name)
            st.setDate(2, Date.valueOf(item.created.toLocalDate()))
            st.execute()
            if (st.generatedKeys.next()) item.id = st.generatedKeys.getInt(1)
        }
        return item
    }

    override fun replace(id: Int, item: Item): Boolean {
        var effected = 0
        ds.execute {
            val st = it.prepareStatement("update items set name = ? where id = ?")
            st.setString(1, item.name)
            st.setInt(2, id)
            effected = st.executeUpdate()
        }
        return effected > 0
    }

    override fun delete(id: Int): Boolean {
        var effected = 0
        ds.execute {
            val st = it.prepareStatement("delete from items where id = ?")
            st.setInt(1, id)
            effected = st.executeUpdate()
        }
        return effected > 0
    }

    override fun findAll(): List<Item> {
        return ds.txBackList {
            val statement = it.prepareStatement("select * from items")
            return@txBackList statement.executeQueryBackList()
        }
    }

    override fun findByName(key: String): List<Item> {
        return ds.txBackList {
            val statement = it.prepareStatement("select * from items where name = ?")
            statement.setString(1, key)
            return@txBackList statement.executeQueryBackList()
        }
    }

    private fun PreparedStatement.executeQueryBackList(): List<Item> {
        val rs = executeQuery()
        val rsl = ArrayList<Item>()
        while (rs.next()) {
            rsl.add(
                Item(
                    rs.getString("name"),
                    rs.getInt("id"),
                    rs.getTimestamp("created").toLocalDateTime(),
                ),
            )
        }
        return rsl
    }

    override fun findById(id: Int): Item? {
        return ds.tx {
            val statement = it.prepareStatement("select * from items where id = ?")
            statement.setInt(1, id)
            val rs = statement.executeQuery()
            if (rs.next()) {
                val item =
                    Item(
                        rs.getString("name"),
                        rs.getInt("id"),
                        rs.getTimestamp("created").toLocalDateTime(),
                    )
                return@tx item
            } else {
                return@tx null
            }
        }
    }

    override fun close() {
        ds.close()
    }
}
