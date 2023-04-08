class ArithmeticOperations(private val x: Int, private val y: Int) {

    fun addition() = this.x + this.y
    fun subtraction() = this.x - this.y
    fun multiplication() = this.x * this.y
    fun division() = if (this.y != 0) this.x / this.y else 0

}