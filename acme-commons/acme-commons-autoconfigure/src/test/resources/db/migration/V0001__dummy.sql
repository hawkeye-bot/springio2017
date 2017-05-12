-- Flyway gooit een exception als er geen sql file gevonden kan worden. Omdat de benodigde SQL scripts door de cjibcommons wordt uitgevoerd, en Flyway hiervoor actief moet zijn,
-- is deze dummy file aanwezig. In een service zal normaliter service-specifieke SQL files aanwezig zijn, waardoor deze dummy file niet nodig is.
CREATE TABLE ACMEDUMMY (
  ID BIGINT
);