package ru.job4j.lambda

class HtmlTable {
    fun table(row: Int, cell: Int): String {
        val table = StringBuilder()
        table.apply {
            append("<table>")
            for (x in 0 until row) {
                append("<tr>")
                for (y in 0 until cell) {
                    append("<th> </th>")
                }
                append("</tr>")
            }
            append("</table>")
        }
        return table.toString()
    }
}
