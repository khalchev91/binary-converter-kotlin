import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import tornadofx.*
import kotlin.math.pow

class HexaView : View() {
    override val root: AnchorPane by fxml("/View/HexaLayout.fxml")

    private val inputText = SimpleStringProperty()
    private val outputText = SimpleStringProperty()
    private val input = SimpleStringProperty()
    private val output = SimpleStringProperty()
    private val inputLabel: Label by fxid()
    private val outputLabel: Label by fxid()
    private val inputField: TextField by fxid("input")
    private val outputField: TextField by fxid("output")

    init {
        inputLabel.bind(inputText)
        outputLabel.bind(outputText)
        inputField.bind(input)
        outputField.bind(output)
        inputText.set("Hexadecimal")
        outputText.set("Decimal")
    }

    fun convert() {
        when {
            inputText.value == "Hexadecimal" -> {
                if (Regex(pattern = "[g-z G-Z]").containsMatchIn(input.value)) {
                    println("invalid input")
                } else {
                    val digits = input.value.toCharArray()
                    var counter = digits.size - 1
                    var count = 0
                    var decimal = 0
                    while (counter >= 0) {
                        when {
                            digits.get(count).toUpperCase() == 'A' -> decimal += (10 * (16.0.pow(counter.toDouble())).toInt())
                            digits.get(count).toUpperCase() == 'B' -> decimal += (11 * (16.0.pow(counter.toDouble())).toInt())
                            digits.get(count).toUpperCase() == 'C' -> decimal += (12 * (16.0.pow(counter.toDouble())).toInt())
                            digits.get(count).toUpperCase() == 'D' -> decimal += (13 * (16.0.pow(counter.toDouble())).toInt())
                            digits.get(count).toUpperCase() == 'E' -> decimal += (14 * (16.0.pow(counter.toDouble())).toInt())
                            digits.get(count).toUpperCase() == 'F' -> decimal += (15 * (16.0.pow(counter.toDouble())).toInt())
                            else -> decimal += (Character.getNumericValue(digits.get(count)) * (16.0.pow(counter.toDouble())).toInt())
                        }
                        counter--
                        count++
                    }
                    output.set(decimal.toString())
                }
            }
            inputText.value == "Decimal" -> {
                if (Regex(pattern = "[a-z A-Z]").containsMatchIn(input.value)) {
                    println("invalid input")
                } else {
                    try {
                        var decimal = Integer.parseInt(input.value)
                        val builder = StringBuilder()
                        while (decimal > 0) {
                            val remainder = decimal % 16
                            when (remainder) {
                                10 -> builder.append("A")
                                11 -> builder.append("B")
                                12 -> builder.append("C")
                                13 -> builder.append("D")
                                14 -> builder.append("E")
                                15 -> builder.append("F")
                                else -> builder.append(remainder)
                            }
                            decimal /= 16
                        }
                        output.set(builder.reverse().toString())
                    } catch (exception: NumberFormatException) {
                        println("invalid input")
                    }
                }
            }
            else -> println("nothing to convert")
        }
    }

    fun reverse() {
        val inputSave = input.value
        val outputSave = output.value
        when {
            inputText.value == "Hexadecimal" -> {
                inputText.set("Decimal")
                outputText.set("Hexadecimal")
                input.set(outputSave)
                output.set(inputSave)
            }
            inputText.value == "Decimal" -> {
                inputText.set("Hexadecimal")
                outputText.set("Decimal")
                input.set(inputSave)
                output.set(outputSave)
            }
        }
    }
}