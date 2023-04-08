class Box(var height: Double, var width: Double, var length: Double) {

    fun getVolume(): Double {
        return this.height * this.width * this.length
    }
}