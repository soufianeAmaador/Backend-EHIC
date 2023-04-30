<h1> WIP: Backend EHIC <h1>
  
  <p>
This is the backend for the EHIC project, a prototype created to demonstrate Self-Sovereign Identity (SSI) and the lifecycle of custom Verifiable Credentials (VC) in the field. The prototype simulates an EU citizen's experience with a digital form of the European Health Insurance Card, from requesting such a certificate from the insurance company to storing it in the user's wallet and finally presenting it at a health clinic.
  </p>
  <p>
The project aims to create, issue and verify the European Health Insurance Card (EHIC) in the form of a Verifiable Credential. The project consists of two web portals: the issuer (Insurance company) and the verifier (Health clinic), a web wallet (User) and this backend server.
  </p>
  <p>
The backend server is an extension of Walt.ids WalletKit, adapted to issue a customized European health insurance card in the form of a Verifiable Credential and other minor adjustments. 
  </p>
  <p>
The application can be run locally or on docker. If the whole application is running on docker, make sure that all ports are properly adjusted.
  </p>
