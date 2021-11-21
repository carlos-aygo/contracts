CREATE TABLE enterprises
(
    id         BIGSERIAL PRIMARY KEY         NOT NULL,
    name       character varying(255) UNIQUE NOT NULL,
    nit        character varying(255) UNIQUE NOT NULL,
    state      character varying(255)        NOT NULL,
    created_at timestamp(0) without time zone NOT NULL
);

CREATE INDEX IF NOT EXISTS "enterprises_state_idx" on enterprises(state);