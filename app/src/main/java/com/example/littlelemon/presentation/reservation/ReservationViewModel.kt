package com.example.littlelemon.presentation.reservation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class ReservationViewModel : ViewModel() {
    private val _selectedSection = MutableStateFlow<ReservationSection>(ReservationInfo())
    internal val selectedSection = _selectedSection.asStateFlow()

    fun onSectionSelected(newSelection: ReservationSection) {
        when (newSelection) {
            is PaymentInfo -> _selectedSection.update { section -> section }
            is ReservationInfo -> _selectedSection.update { section -> section }
            is ReviewInfo -> _selectedSection.update { section -> section }
        }
    }
}

internal sealed class ReservationSection(open val section: String) {
    companion object {
        val allSections = listOf(ReservationInfo(), PaymentInfo(), ReviewInfo())
    }
}
internal data class ReservationInfo(override val section: String = "Reserve Info") : ReservationSection(section)
internal data class PaymentInfo(override val section: String = "Payment Info") : ReservationSection(section)
internal data class ReviewInfo(override val section: String = "Review") : ReservationSection(section)
