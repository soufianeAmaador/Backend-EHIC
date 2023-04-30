package id.walt.webwallet.backend.config

import com.beust.klaxon.Klaxon
import id.walt.common.prettyPrint
import id.walt.credentials.w3c.VerifiableCredential
import id.walt.credentials.w3c.templates.VcTemplateManager
import id.walt.customTemplates.EHIC
import id.walt.model.oidc.OIDCProvider
import java.io.File
import java.nio.file.Paths

data class WalletConfig(
    @ExternalHostnameUrl val walletUiUrl: String = "http://localhost:8080",
    @ExternalHostnameUrl val walletApiUrl: String = "http://localhost:8080/api",
    var issuers: Map<String, OIDCProvider> = mapOf()
) {
    companion object {
        val CONFIG_FILE = "${id.walt.WALTID_DATA_ROOT}/config/wallet-config.json"
        val ISSUERS_SECRETS = "${id.walt.WALTID_DATA_ROOT}/secrets/issuers.json"
        lateinit var config: WalletConfig

        init {
            val path = Paths.get("").toAbsolutePath().toString()

            val cf = File(CONFIG_FILE)
            if (cf.exists()) {
                config = Klaxon().fieldConverter(ExternalHostnameUrl::class, externalHostnameUrlValueConverter)
                    .parse<WalletConfig>(cf) ?: WalletConfig()
            } else {
                config = WalletConfig()
            }

            val issuerSecretsFile = File(ISSUERS_SECRETS)
            val issuerSecrets = when (issuerSecretsFile.exists()) {
                true -> Klaxon().parse<SecretConfigMap>(issuerSecretsFile) ?: SecretConfigMap(mapOf())
                else -> SecretConfigMap(mapOf())
            }

            config.issuers = config.issuers.values.associate { issuer ->
                issuer.id to issuer.withSecret(issuerSecrets.secrets[issuer.id]).withExternalHostnameUrl()
            }

        }
    }
}

