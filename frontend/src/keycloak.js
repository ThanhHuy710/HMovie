import Keycloak from "keycloak-js";
import { KEYCLOACK_CONFIG } from "./configurations/configuration";

const keycloak = new Keycloak({
  url: KEYCLOACK_CONFIG.url,
  realm: KEYCLOACK_CONFIG.realm,
  clientId: KEYCLOACK_CONFIG.clientId,
});

const loginWithGoogle = async () => {
  try {
    await keycloak.init({ onLoad: 'check-sso' });
    keycloak.login({
      idpHint: 'google'
    });
  } catch (error) {
    console.error("Keycloak init failed:", error);
  }
};

const registerWithGoogle = async () => {
  try {
    await keycloak.init({ onLoad: 'check-sso' });
    keycloak.login({
      idpHint: 'google',
      action: 'register'
    });
  } catch (error) {
    console.error("Keycloak init failed:", error);
  }
};

export { keycloak, loginWithGoogle, registerWithGoogle };
