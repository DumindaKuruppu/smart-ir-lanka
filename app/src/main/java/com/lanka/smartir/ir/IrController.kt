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

    fun getCarrierFrequencies(): Array<ConsumerIrManager.CarrierFrequencyRange>? {
        return irManager?.carrierFrequencies
    }
}
