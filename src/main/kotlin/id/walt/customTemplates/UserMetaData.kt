package model

data class Address(
    var street: String,
    var houseNumber: String,
    var postcode: String,
    var residence: String,
    var municipality: String? = null,
    var country: String,
    var addressType: String ? = null
)

data class BankDetails(
    var bankName: String,
    var bankCode: String,
    var accountNumber: String
)

data class Insurance(
    var startDate: String,
    var endDate: String,
    var insuranceType: String
)

data class Telephone(
    var phoneNumber: String,
    var numberType: String
)