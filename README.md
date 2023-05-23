<div align="center">
 <h1>Backend EHIC</h1>
 <span><b> Work in progress </b></span></a>
 <p>Backend for the EHIC project: Issue, store and verify a custom European Health Insurance Card(EHIC) in the form of a verifiable credential<p>
</div>
  
  <p>
This is the backend for the EHIC project, a prototype created to demonstrate <b>Self-Sovereign Identity</b> (SSI) and the lifecycle of custom <b>Verifiable Credentials</b> (VC) in the field. The prototype simulates an EU citizen's experience with a digital form of the European Health Insurance Card, from requesting such a certificate from the insurance company to storing it in the user's <b>wallet</b> and finally presenting it at a health clinic.
  </p>
 
  <p>
  The project aims to <b>create</b>, <b>issue</b> and <b>verify</b> the European Health Insurance Card (EHIC) in the form of a Verifiable Credential. The project consists of two web portals: the issuer (Insurance company) and the verifier (Health clinic), a web wallet (User) and this backend server.
  </p>
  <p>
The backend server is an extension of Walt.ids WalletKit, adapted to issue a customized European health insurance card in the form of a Verifiable Credential and other minor adjustments. 
  </p>
  <p>
The application can be run locally or on docker. If the whole application is running on docker, make sure that all ports are properly adjusted.
  </p>


## Services
* **Verifiable Credential and Presentation exchange**
  * Support for credential presentation exchange based on OIDC-SIOPv2 spec

### Verifier portal backend (Portal being built)
* **Presentation exchange**
  * Support for presentation exchange based on OIDC-SIOPv2 spec

### Issuer portal backend

* **Verifiable credential issuance**
  * Support for issuing verifiable credentials to the web wallet, based on OIDC-SIOPv2 spec

## Usage

Configuration and data are kept in sub folders of the data root:
* `config/`
* `data/`

Data root is by default the current **working directory**.

It can be overridden by specifying the **environment variable**: 

`WALTID_DATA_ROOT`

### Verifier portal and wallet configuration:

**config/verifier-config.json**

```
{
  "verifierUiUrl": "http://localhost:4000",                 # URL of verifier portal UI
  "verifierApiUrl": "http://localhost:8080/verifier-api",   # URL of verifier portal API
  "wallets": {                                              # wallet configuration
    "walt.id": {                                            # wallet configuration key
      "id": "walt.id",                                      # wallet ID
      "url": "http://localhost:3000",                       # URL of wallet UI
      "presentPath": "CredentialRequest",                   # URL subpath for a credential presentation request
      "receivePath" : "ReceiveCredential/",                 # URL subpath for a credential issuance request
      "description": "walt.id web wallet"                   # Wallet description
    }
  }
}
```

### Issuer portal and wallet configuration:

**config/issuer-config.json**

```
{
  "issuerUiUrl": "http://localhost:5000",                   # URL of issuer portal UI
  "issuerApiUrl": "http://localhost:8080/issuer-api",       # URL of issuer portal API (needs to be accessible from the walletkit)
  "wallets": {                                              # wallet configuration
    "walt.id": {                                            # wallet configuration key
      "id": "walt.id",                                      # wallet ID
      "url": "http://localhost:3000",                       # URL of wallet UI
      "presentPath": "CredentialRequest",                   # URL subpath for a credential presentation request
      "receivePath" : "ReceiveCredential/",                 # URL subpath for a credential issuance request
      "description": "walt.id web wallet"                   # Wallet description
    }
  }
}
```

### Wallet backend configuration

User data (dids, keys, credentials) are currently stored under

`data/<user@email.com>`


## Build & run the Web Wallet Kit

_Gradle_ or _Docker_ can be used to build this project independently. Once running, one can access the Swagger API at http://localhost:8080/api/swagger

### Gradle

    gradle build

unzip package under build/distributions and switch into the new folder. Copy config-files _service-matrix.properties_ and _signatory.conf_ from the root folder and run the bash-script:

    ./bin/waltid-walletkit
    
To run the backend you will execute:
   ```waltid-walletkit run``` 
To have issuers, you will have to execute: 
   ```waltid-walletkit --init-issuer```

### Docker

    docker build -t waltid/walletkit .

    docker run -it -p 8080:8080 waltid/walletkit

## Running all components with Docker Compose

To spawn the **backend** together with the **wallet frontend**, the **issuer-** and the **verifier-portal**, one can make use of the docker-compose configuration located in folder:

`./docker/`

In order to simply run everything, enter:

    docker-compose up

This configuration will publish the following endpoints by default:
* **web wallet** on _**[HOSTNAME]:8080**_
  * wallet frontend: http://[HOSTNAME]:8080/
  * wallet API: http://[HOSTNAME]:8080/api/
* **verifier portal** on _**[HOSTNAME]:8081**_
  * verifier frontend: http://[HOSTNAME]:8081/
  * verifier API: http://[HOSTNAME]:8081/verifier-api/
* **issuer portal** on _**[HOSTNAME]:8082**_
  * issuer frontend: http://[HOSTNAME]:8082/
  * issuer API: http://[HOSTNAME]:8082/issuer-api/

*Note*

**[HOSTNAME]** is your local computer name. Using **localhost**, not all features will work correctly.

Visit the `./docker`. folder for adjusting the system config in the following files
* **docker-compose.yaml** - Docker config for launching containers, volumes & networking
* **ingress.conf** - Routing config
* **config/verifier-config.json** - verifier portal configuration
* **config/issuer-config.json** - issuer portal configuration

## License

Licensed under the [Apache License, Version 2.0](https://github.com/walt-id/waltid-walletkit/blob/master/LICENSE)
