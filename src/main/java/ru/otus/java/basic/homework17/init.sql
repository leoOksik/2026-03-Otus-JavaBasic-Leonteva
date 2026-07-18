CREATE SCHEMA IF NOT EXISTS testing;

CREATE table testing.test
(
    id          UUID PRIMARY KEY      DEFAULT uuidv7(),
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(512),
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE table testing.question
(
    id            UUID PRIMARY KEY DEFAULT uuidv7(),
    question_text text NOT NULL,
    test_id       UUID NOT NULL
);

CREATE table testing.option
(
    id            UUID PRIMARY KEY      DEFAULT uuidv7(),
    answer_option VARCHAR(512) NOT NULL,
    is_correct    BOOLEAN      NOT NULL DEFAULT FALSE,
    question_id   UUID         NOT NULL
);

ALTER TABLE testing.question
    ADD CONSTRAINT fk_question_test FOREIGN KEY (test_id) REFERENCES testing.test (id) ON DELETE CASCADE;

ALTER TABLE testing.option
    ADD CONSTRAINT fk_option_question FOREIGN KEY (question_id) REFERENCES testing.question (id) ON DELETE CASCADE;

CREATE INDEX idx_question_test_id ON testing.question (test_id);
CREATE INDEX idx_option_question_id ON testing.option (question_id);
