package com.example.littlelemon.presentation.reservation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class ReservationViewModel : ViewModel() {

    private val _customer: MutableStateFlow<Customer> = MutableStateFlow(Customer())
    internal val customer = _customer.asStateFlow()

    private val _showSnackBar = MutableStateFlow(false)
    internal val showSnackBar = _showSnackBar.asStateFlow()

    private val _gotoHome = MutableStateFlow(false)
    internal val gotoHome = _gotoHome.asStateFlow()

    private val _selectedSection = MutableStateFlow<ReservationSection>(ReservationSection.firstSection)
    internal val selectedSection = _selectedSection.asStateFlow()

    private val _cvv = MutableStateFlow<String>("")
    internal val cvv = _cvv.asStateFlow()

    private val _cardNum = MutableStateFlow<String>("")
    internal val cardNum = _cardNum.asStateFlow()

    private val _expDate = MutableStateFlow<Long?>(null)
    internal val expDate = _expDate.asStateFlow()

    private val _address = MutableStateFlow<String>("")
    internal val address = _address.asStateFlow()

    private val _email = MutableStateFlow<String>("")
    internal val email = _email.asStateFlow()

    private val _phoneNum = MutableStateFlow<String>("")
    internal val phoneNum = _phoneNum.asStateFlow()

    private val _date = MutableStateFlow<Long?>(null)
    internal val date = _date.asStateFlow()

    private val _time = MutableStateFlow<Time?>(null)
    internal val time = _time.asStateFlow()

    private val _customerReserveInfo = MutableStateFlow<CustomerReservation>(
        CustomerReservation(
            fullName = "",
            email = "",
            phoneNum = "",
            address = ""
        )
    )
    internal val customerReserveInfo = _customerReserveInfo.asStateFlow()

    private val _honorific = MutableStateFlow<Honorifics>(Mr())
    internal val honorific = _honorific.asStateFlow()

    private val _firstName = MutableStateFlow<String>("")
    internal val firstName = _firstName.asStateFlow()

    private val _middleNames = MutableStateFlow<String>("")
    internal val middleNames = _middleNames.asStateFlow()

    private val _surname = MutableStateFlow<String>("")
    internal val surname = _surname.asStateFlow()

    private val _attendants = MutableStateFlow<Short>(0)
    internal val attendants = _attendants.asStateFlow()

    internal fun dismissSnackBar() = _showSnackBar.update { false }
    internal fun onAttendantInc() = _attendants.update { it.inc() }

    internal fun onAttendantDec() = _attendants.update { if (it > 0) it.dec() else it }

    internal fun onTimeChange(newTime: Time?) = _time.update { newTime }

    internal fun onSurnameChange(newSurname: String) = _surname.update { newSurname }

    internal fun onMiddleNamesChange(newMiddleNames: String) = _middleNames.update { newMiddleNames }

    internal fun onFirstNameChange(newFirstName: String) = _firstName.update { newFirstName }

    internal fun onHonorificChange(newHonorific: Honorifics) =  _honorific.update { newHonorific }

    internal fun onPhoneNumChange(newPhoneNum: String) = _phoneNum.update { newPhoneNum }

    internal fun onCvvChange(newCvv: String) = _cvv.update { newCvv }
    internal fun onCardNumChange(newCardNum: String) = _cardNum.update { newCardNum }
    internal fun onExpDateChange(newExpDate: Long?) = _expDate.update { newExpDate }

    internal fun onDateChange(newDate: Long?) = _date.update { newDate }

    internal fun onAddressChange(newAddress: String) = _address.update { newAddress }

    internal fun onEmailChange(newEmail: String) = _email.update { newEmail }

    internal fun onClickProceed(): Unit = when(selectedSection.value) {
        is PaymentInfo -> {
            _customer.update {
                it.copy(
                    payeeInfo = Payee(
                        cvv = _cvv.value,
                        cardNum = _cardNum.value,
                        expDate = _expDate.value,
                        address = _address.value,
                        phoneNum = _phoneNum.value,
                        email = _email.value
                    )
                )
            }
            _selectedSection.update { section ->
                 ReservationSection.nextSection(section)
            }
        }
        is ReservationInfo -> {
            _customer.update {
                it.copy(
                    salutation = _honorific.value,
                    fullName = _firstName.value + _middleNames.value.toInitials() + _surname.value,
                    attendants = _attendants.value.toInt(),
                    date = _date.value,
                    time = _time.value,
                )
            }
            _selectedSection.update {  section -> ReservationSection.nextSection(section)  }
        }
        is ReviewInfo -> {
            if (!_customer.value.isValid())
                _showSnackBar.update { true }
            else {
                _gotoHome.update { true }
            }

        }
    }

    fun onSectionSelected(newSelection: ReservationSection) {
        if (_selectedSection.value is PaymentInfo)
            _customer.update {
                it.copy(
                    payeeInfo = Payee(
                        cvv = _cvv.value,
                        cardNum = _cardNum.value,
                        expDate = _expDate.value,
                        address = _address.value,
                        phoneNum = _phoneNum.value,
                        email = _email.value
                    )
                )
            }

        if (_selectedSection.value is ReservationInfo)
            _customer.update {
                it.copy(
                    salutation = _honorific.value,
                    fullName = _firstName.value + _middleNames.value.toInitials() + _surname.value,
                    attendants = _attendants.value.toInt(),
                    date = _date.value,
                    time = _time.value,
                )
            }

        when (newSelection) {
            is PaymentInfo -> _selectedSection.update { section -> newSelection }
            is ReservationInfo -> _selectedSection.update { section -> newSelection }
            is ReviewInfo -> _selectedSection.update { section -> newSelection }
        }
    }
}

internal sealed class ReservationSection(open val section: String) {
    companion object {
        val allSections = listOf(ReservationInfo(), PaymentInfo(), ReviewInfo())
        val firstSection = ReservationInfo()
        fun nextSection(section: ReservationSection) = when (section) {
            is PaymentInfo -> ReviewInfo()
            is ReservationInfo -> PaymentInfo()
            is ReviewInfo -> ReservationInfo()
        }
    }
}
internal data class ReservationInfo(override val section: String = "Reserve Info") : ReservationSection(section)
internal data class PaymentInfo(override val section: String = "Payment Info") : ReservationSection(section)
internal data class ReviewInfo(override val section: String = "Review") : ReservationSection(section)

internal data class CustomerReservation(
    val salutation: Honorifics? = null,
    val fullName: String,
    val attendants: Int? = 0,
    val date: Long? = null,
    val time: Time? = null,
    val email: String,
    val phoneNum: String,
    val address: String
)

internal data class Customer(
    val salutation: Honorifics? = null,
    val fullName: String = "",
    val attendants: Int? = 0,
    val date: Long? = null,
    val time: Time? = null,
    val payeeInfo: Payee = Payee(
        cvv = "",
        cardNum = "",
        expDate = null,
        address = "",
        phoneNum = "",
        email = ""
    )
) {
    internal fun isValid(): Boolean {
        if (salutation == null)
            return false
        if (attendants == null)
            return false
        if (date == null)
            return false
        if (time == null)
            return false
        if (fullName.isEmpty() || fullName.isBlank())
            return false
        if (!payeeInfo.isValid())
            return false
        return true
    }
    internal fun toCustomerReservation(): CustomerReservation = CustomerReservation(
        salutation = salutation,
        fullName = fullName,
        attendants = attendants,
        date = date,
        time = time,
        email = payeeInfo.email,
        phoneNum = payeeInfo.phoneNum,
        address = payeeInfo.address
    )



}
internal data class Time(val hr: Short, val min: Short) {
    override fun toString(): String {
        return "$hr $min Hrs"
    }
}


internal data class Payee(
    val cvv: String,
    val cardNum: String,
    val expDate: Long?,
    val address: String,
    val phoneNum: String,
    val email: String
) {
    internal fun isValid(): Boolean {
        if (expDate == null)
            return false
        if (cvv.isEmpty() || cvv.isBlank())
            return false
        if (address.isBlank() || address.isEmpty() )
            return false
        return true
    }
}


internal sealed class Honorifics(open val name: String) {
    companion object {
        val list = listOf<Honorifics>(
            Mr(),
            Mrs(),
            Ms()
        )
    }
}

internal data class Mr(override val name: String = "Mr") : Honorifics(name)
internal data class Mrs(override val name: String = "Mrs") : Honorifics(name)
internal data class Ms(override val name: String = "Ms") : Honorifics(name)


private fun String.toInitials(): String = if (isBlank() || isEmpty()) "" else trim()
    .split(" ")
    .map { it.first() }
    .joinToString(
        separator = ". ",
        prefix = " ",
        postfix = ". "
    )

