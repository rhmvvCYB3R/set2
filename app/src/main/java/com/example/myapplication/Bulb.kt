package com.example.myapplication

class Bulb {
    private var isOn: Boolean = false
    private var isBurned: Boolean = false

    fun turnOn() {
        if (!isBurned) isOn = true
    }

    fun turnOff() {
        isOn = false
    }

    fun isOn(): Boolean = isOn

    fun isBurned(): Boolean = isBurned

    fun burn() {
        isBurned = true
        isOn = false
    }
}