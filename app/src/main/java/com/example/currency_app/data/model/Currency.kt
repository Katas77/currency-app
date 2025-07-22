package com.example.currency_app.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Valute", strict = false)
data class Currency(
    @field:Element(name = "CharCode") var charCode: String = "",
    @field:Element(name = "Name") var name: String = "",
    @field:Element(name = "Value") var value: String = ""
)
