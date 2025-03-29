-- Create books table
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    book_code VARCHAR(10) UNIQUE NOT NULL,
    status VARCHAR(20) DEFAULT 'active',
    image VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create index for book_code
CREATE INDEX idx_books_book_code ON books(book_code);

CREATE TABLE book_translations (
    book_id BIGINT NOT NULL,
    locale VARCHAR(5) NOT NULL,  -- en, vi, fr, es
    book_name TEXT NOT NULL,
    book_description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (book_id, locale),
    CONSTRAINT fk_book_translations_books FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- Create index for JOIN and find by book_id and locale
CREATE INDEX idx_book_translations_book_id ON book_translations(book_id);
CREATE INDEX idx_book_translations_locale ON book_translations(locale);

CREATE TABLE paragraphs (
    id BIGSERIAL PRIMARY KEY,
    paragraph_order INT NOT NULL DEFAULT 1,
    image VARCHAR(255) NULL,
    book_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_paragraphs_books FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- Create index to optimise the query for book_id
CREATE INDEX idx_paragraphs_book_id ON paragraphs(book_id);

CREATE TABLE paragraph_translations (
    paragraph_id BIGINT NOT NULL,
    locale VARCHAR(5) NOT NULL,  -- en, vi, fr, es
    paragraph_content TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (paragraph_id, locale),
    CONSTRAINT fk_paragraph_translations_paragraphs FOREIGN KEY (paragraph_id) REFERENCES paragraphs(id) ON DELETE CASCADE
);

CREATE INDEX idx_paragraph_translations_paragraph_id ON paragraph_translations(paragraph_id);

CREATE TABLE resources (
    id BIGSERIAL PRIMARY KEY,
    link_to_resource VARCHAR(255) NOT NULL,
    resource_type VARCHAR(20),
    description VARCHAR(255),
    paragraph_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_resources_paragraphs FOREIGN KEY (paragraph_id) REFERENCES paragraphs(id) ON DELETE CASCADE
);

CREATE INDEX idx_resources_paragraph_id ON resources(paragraph_id);