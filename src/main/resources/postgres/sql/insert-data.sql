/* INSERT QUERY */
INSERT INTO title(id, name, deleted, used)
VALUES
  (1, 'Ryuhas home', 1, 0);


/* INSERT QUERY */
INSERT INTO code(id, name)
VALUES
  (1, 'picture');
/* INSERT QUERY */
INSERT INTO code(id, name)
VALUES
  (2, 'animation');
/* INSERT QUERY */
INSERT INTO code(id, name)
VALUES
  (3, 'game');


/* INSERT QUERY */
INSERT INTO category(
  id, name, display_order, deleted, url
)
VALUES
  (1, 'Picture', 1, 1, '/picture');
/* INSERT QUERY */
INSERT INTO category(
  id, name, display_order, deleted, url
)
VALUES
  (2, 'Game', 2, 1, '/game');
/* INSERT QUERY */
INSERT INTO category(
  id, name, display_order, deleted, url
)
VALUES
  (
    3, 'Communication', 3, 1, '/communication'
  );
