CREATE TABLE IF NOT EXISTS joke (
    id serial primary key ,
    cat_id INTEGER,
    likes INTEGER default(0),
    dislikes INTEGER default(0),
    diff integer default(0),
    joke text not null,
    FOREIGN KEY (cat_id) REFERENCES category(id)
);