package model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class User(
    @JsonProperty("name") var name: String,
    @JsonProperty var givenNames: String,
    @JsonProperty var dateOfBirth: LocalDate,
    @JsonProperty var personalIdentificationNumber: String,
    @JsonProperty var identificationOfTheInstitution: String,
    @JsonProperty var identificationNumberOfTheCard: String,
    @JsonProperty var insurer: Insurer,
    @JsonProperty var expiryDate: LocalDate,
    @JsonProperty var bankDetails: BankDetails,
    @JsonProperty var address: Address,
    @JsonProperty var telephone: Telephone
)



