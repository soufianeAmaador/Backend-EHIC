package id.walt.customTemplates

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import model.*


data class EHIC(
    @Json(name = "@context") var context: List<String>? = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) override var credentialSubject: CredentialSubject? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null, // "2024-01-01T20:38:38Z"
    @Json(serializeNull = false) override var id: String? = "did:ebsi:zsSgDXeYPhZ3AuKhTFneDf1", // "urn:uuid:27e83c2d-d230-43a7-9229-2e72d3570a27"
    @Json(serializeNull = false) override var issued: String? = "2022-12-30T15:25:20Z", // "2022-12-30T15:25:20Z"
    @Json(serializeNull = false) override var issuer: String? = "did:ebsi:z25Hi99z7n2tyKnfkU3p6SyW", // "did:ebsi:z25Hi99z7n2tyKnfkU3p6SyW"
    @Json(serializeNull = false) override val credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<EHIC.CredentialSubject>(type) {
    data class CredentialStatus(
        @Json(serializeNull = false) var id: String? = null, // "https://essif.europa.eu/status/identity#verifiableID#1dee355d-0432-4910-ac9c-70d89e8d674e"
        @Json(serializeNull = false) var type: String? = null // "CredentialStatusList2020"
    )

    data class CredentialSubject(
        @Json(serializeNull = false) override var id: String? = "did:ebsi:zsSgDXeYPhZ3AuKhTFneDf1", // "urn:uuid:27e83c2d-d230-43a7-9229-2e72d3570a27",
        @Json(serializeNull = false) var name: String? = null, // "Amaador"
        @Json(serializeNull = false) var givenNames: String? = null, // "Soufiane"
        @Json(serializeNull = false) var dateOfBirth: String? = null, // "02/04/1999"
        @Json(serializeNull = false) var personalIdentificationNumber: String? = null, // "012345678"
        @Json(serializeNull = false) var identificationOfTheInstitution: String? = null, // "3311 - Zilveren Kruis"
        @Json(serializeNull = false) var identificationNumberOfTheCard: String? = null, // "01234567890123456789"
        @Json(serializeNull = false) var expiryDate: String? = null, // "12/12/2024"
        @Json(serializeNull = false) var insurer: model.Insurer? = null,
        @Json(serializeNull = false) var bankDetails: model.BankDetails? = null,
        @Json(serializeNull = false) var address: model.Address? = null,
        @Json(serializeNull = false) var telephone: model.Telephone? = null
    ) : id.walt.vclib.model.CredentialSubject() {
        data class Insurer(
            @Json(serializeNull = false) var identificationNumber: String? = null, // "3311"
            @Json(serializeNull = false) var organisationName: String? = null, // "Zilveren Kruis"
            @Json(serializeNull = false) var polisNumber: String? = null, // "12345678"
            @Json(serializeNull = false, name = "Insurance") var insurance: Insurance? = null,
            @Json(serializeNull = false, name = "Address") var address: Address? = null,
            @Json(serializeNull = false, name = "Telephone") var telephone: Telephone? = null
        ) {
            data class Insurance(
                @Json(serializeNull = false) var startDate: String? = null, // "01-01-2023"
                @Json(serializeNull = false) var endDate: String? = null, // "31-01-2024"
                @Json(serializeNull = false) var insuranceType: String? = null // "Basic Insured"
            )
        }

        data class BankDetails(
            @Json(serializeNull = false) var bankName: String? = null, // "ING"
            @Json(serializeNull = false) var bankCode: String? = null, // "INGBNL2A"
            @Json(serializeNull = false) var accountNumber: String? = null // "NL85INGB0001234567"
        )

        data class Address(
            @Json(serializeNull = false) var street: String? = null, // "1e Jacob van Campenstr"
            @Json(serializeNull = false) var houseNumber: String? = null, // "15"
            @Json(serializeNull = false) var postcode: String? = null, // "1012 NX "
            @Json(serializeNull = false) var residence: String? = null, // "Hoogmade"
            @Json(serializeNull = false) var municipality: String? = null, // "Kaag en Braassem"
            @Json(serializeNull = false) var country: String? = null, // "Netherlands"
            @Json(serializeNull = false) var addressType: String? = null // "Residential/residence address"
        )

        data class Telephone(
            @Json(serializeNull = false) var phoneNumber: String? = null, // "+31505233333"
            @Json(serializeNull = false) var numberType: String? = null // "Business"
        )

    }
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "EuropeanHealthInsuranceCard"),
        template = {
            EHIC(
                credentialStatus = CredentialStatus(
                    id = "https://api-pilot.ebsi.eu/trusted-schemas-registry/v1/schemas/",
                    type = "CredentialStatusList2020"
                ),
                credentialSubject = CredentialSubject(
                    id = "did:ebsi:123456789",
                    name ="Doe",
                    givenNames = "John",
                    dateOfBirth = "1999-04-02",
                    personalIdentificationNumber = "012345678",
                    identificationOfTheInstitution =  "3311 - Zilveren Kruis",
                    identificationNumberOfTheCard = "01234567890123456789",
                    insurer = Insurer(
                        identificationNumber = "3311",
                        organisationName = "Zilveren Kruis",
                        polisNumber = "12345678",
                        insurance = Insurance(
                            startDate = "2023-01-01",
                            endDate = "2024-01-31",
                            insuranceType = "Basic Insured"
                        ),
                        address = Address(
                            street = "Postbus",
                            houseNumber = "34000",
                            postcode = "7500 KC",
                            residence = "Enschede",
                            country = "Netherlands"
                        ),
                        telephone = Telephone(
                            phoneNumber = "+31505233333",
                            numberType = "Business"
                        )
                    ),
                    expiryDate = "2024-01-31",
                    bankDetails = BankDetails(
                        bankName = "ING",
                        bankCode = "INGBNL2A",
                        accountNumber = "NL85INGB0001234567"
                    ),
                    address = Address(
                        street = "1e Jacob van Campenstr",
                        houseNumber = "15",
                        postcode = "1012 NX",
                        residence = "Hoogmade",
                        municipality = "Kaag en Braasem",
                        addressType = "Residential/residence address",
                        country = "Netherlands"
                    ),
                    telephone = Telephone(
                        phoneNumber = "+311725233111",
                        numberType = "private"
                    )

                ),
                issuer = "did:ebsi:zr2rWDHHrUCdZAW7wsSb5nQ", //SIGNATORY DID
            )
        })
}