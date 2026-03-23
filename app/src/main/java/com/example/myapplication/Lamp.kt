package com.example.myapplication

class Lamp {
    private var isOn: Boolean = false
    private var intensity: Int = 0
    private var bulb: Bulb = Bulb()

    fun turnOn() {
        if (bulb.isBurned()) {
            isOn = false
            intensity = 0
            bulb.turnOff()
        } else {
            isOn = true
            if (intensity == 0) intensity = 1
            bulb.turnOn()
        }
    }

    fun turnOff() {
        isOn = false
        intensity = 0
        bulb.turnOff()
    }

    fun brighten() {
        if (!isOn) return
        intensity++
        if (intensity > 10) {
            bulb.burn()
            intensity = 0
            isOn = false
        }
    }

    fun dim() {
        if (!isOn) return
        intensity--
        if (intensity <= 0) {
            intensity = 0
            isOn = false
            bulb.turnOff()
        }
    }

    fun replaceBulb(): Boolean {
        return if (isOn) false
        else {
            bulb = Bulb()
            true
        }
    }

    fun isOn(): Boolean = isOn

    fun isShining(): Boolean = isOn && intensity > 0 && bulb.isOn()

    fun isBulbBurned(): Boolean = bulb.isBurned()

    fun getIntensity(): Int = intensity
}