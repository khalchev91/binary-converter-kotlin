import javafx.scene.control.TabPane
import tornadofx.*

class MainLayout : View() {

    private val binaryLayout: BinaryView by inject()
    private val octalLayout: OctalView by inject()
    private val hexaDecimal: HexaView by inject()
    override val root = tabpane {
        style {
            setPrefHeight(400.0)
            setPrefWidth(600.0)
        }
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

        tab("Binary") {
            this.add(binaryLayout)
        }
        tab("Octal") {
            this.add(octalLayout)
        }

        tab("Hexadecimal") {
            this.add(hexaDecimal)
        }
    }
}

