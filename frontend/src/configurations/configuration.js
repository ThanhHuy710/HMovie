export const KEYCLOACK_CONFIG = {
  url: import.meta.env.VITE_KEYCLOAK_URL || "http://localhost:8180",
  realm: "HMovie",
  clientId: "Hmovie_WebApp",
};
