import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import tornadofx.*
import kotlin.math.pow

class BinaryView : View() {
    override val root: AnchorPane by fxml("/View/BinaryLayout.fxml")
    private val inputText = SimpleStringProperty()
    private val outputText = SimpleStringProperty()
    private val input = SimpleStringProperty()
    private val output = SimpleStringProperty()
    private val inputLabel: Label by fxid()
    private val outputLabel: Label by fxid()
    private val inputField: TextField by fxid("input")
    private val outputField: TextField by fxid("output")

    private var error = true

    init {
        inputField.bind(input)
        outputField.bind(output)
        inputLabel.bind(inputText)
        outputLabel.bind(outputText)
        inputText.set("Binary")
        outputText.set("Decimal")
        error = true
    }

    fun convert() {
        when {
            inputText.value == "Binary" -> {
                if (Regex(pattern = "[a-z A-Z2-9]").containsMatchIn(input.value)) {
                    error = false
                } else {
                    val digits = input.value.toCharArray()
                    var counter = digits.size - 1
                    var count = 0
                    var decimal = 0
                    while (counter >= 0) {
                        decimal += (Character.getNumericValue(digits[count]) * (2.0.pow(counter.toDouble())).toInt())
                        counter--
                        count++
                    }
                    output.set(decimal.toString())
                }
            }
            inputText.value == "Decimal" -> {
                if (Regex(pattern = "[a-z A-Z]").containsMatchIn(input.value)) {
                    error = false
                } else {
                    try {
                        var decimal = Integer.parseInt(input.value)
                        val builder = StringBuilder()
                        while (decimal > 0) {
                            builder.append(decimal % 2)
                            decimal /= 2
                        }
                        output.set(builder.reverse().toString())
                    } catch (exception: NumberFormatException) {
                        error = false
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
            inputText.value == "Binary" -> {
                inputText.set("Decimal")
                outputText.set("Binary")
                input.set(outputSave)
                output.set(inputSave)
            }
            inputText.value == "Decimal" -> {
                inputText.set("Binary")
                outputText.set("Decimal")
                input.set(inputSave)
                output.set(outputSave)
            }
        }
    }
}
