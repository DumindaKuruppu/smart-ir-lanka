package com.lanka.smartir.ir

import android.content.Context
import android.hardware.ConsumerIrManager

class IrController(context: Context) {
    private val irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

    fun hasIrEmitter(): Boolean {
        return irManager?.hasIrEmitter() ?: false
    }

    fun transmit(carrierFrequency: Int, pattern: IntArray) {
        irManager?.transmit(carrierFrequency, pattern)
    }

    fun transmitPulseDistanceWidth(
        frequency: Int,
        headerMark: Int,
        headerSpace: Int,
        oneMark: Int,
        oneSpace: Int,
        zeroMark: Int,
        zeroSpace: Int,
        data: Long,
        numBits: Int,
        lsbFirst: Boolean = true
    ) {
        val pattern = mutableListOf<Int>()

        // Header
        pattern.add(headerMark)
        pattern.add(headerSpace)

        // Data
        for (i in 0 until numBits) {
            val bit = if (lsbFirst) {
                (data shr i) and 1L
            } else {
                (data shr (numBits - 1 - i)) and 1L
            }

            if (bit == 1L) {
                pattern.add(oneMark)
                pattern.add(oneSpace)
            } else {
                pattern.add(zeroMark)
                pattern.add(zeroSpace)
            }
        }

        // Final trail mark to signal the end of the last space
        pattern.add(if (numBits > 0 && ((data shr (if (lsbFirst) numBits - 1 else 0)) and 1L) == 1L) oneMark else zeroMark)

        transmit(frequency * 1000, pattern.toIntArray())
    }

    // Panasonic Fan Specific Commands
    fun sendPanasonicFanPower() {
        transmitPulseDistanceWidth(38, 3550, 3500, 900, 2650, 900, 900, 0x7C483B, 24)
    }

    fun sendPanasonicFanSpeed() {
        transmitPulseDistanceWidth(38, 3600, 3450, 950, 2550, 950, 800, 0x8047FB, 24)
    }

    fun sendPanasonicFanOscillation() {
        transmitPulseDistanceWidth(38, 3550, 3500, 850, 2650, 850, 900, 0x88477B, 24)
    }

    fun sendPanasonicFanTimer() {
        transmitPulseDistanceWidth(38, 3550, 3500, 900, 2600, 900, 850, 0x8447BB, 24)
    }

    fun getCarrierFrequencies(): Array<ConsumerIrManager.CarrierFrequencyRange>? {
        return irManager?.carrierFrequencies
    }
}
