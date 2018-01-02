import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import tornadofx.*
import kotlin.math.pow

class OctalView : View() {
    override val root: AnchorPane by fxml("/View/OctalLayout.fxml")

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
        inputText.set("Octal")
        outputText.set("Decimal")
    }

    fun convert() {
        when {
            inputText.value == "Octal" -> {
                if (Regex(pattern = "[a-z A-Z8-9]").containsMatchIn(input.value)) {
                    println("invalid input")
                } else {
                    val digits = input.value.toCharArray()
                    var counter = digits.size - 1
                    var count = 0
                    var decimal = 0
                    while (counter >= 0) {
                        decimal += (Character.getNumericValue(digits[count]) * (8.0.pow(counter.toDouble())).toInt())
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
                            builder.append(decimal % 8)
                            decimal /= 8
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
            inputText.value == "Octal" -> {
                inputText.set("Decimal")
                outputText.set("Octal")
                input.set(outputSave)
                output.set(inputSave)
            }
            inputText.value == "Decimal" -> {
                inputText.set("Octal")
                outputText.set("Decimal")
                input.set(inputSave)
                output.set(outputSave)
            }
        }
    }
}