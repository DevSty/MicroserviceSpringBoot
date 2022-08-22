INSERT INTO genero (genero_id, genero_type) VALUES (1,'Masculino') ON CONFLICT (genero_id) DO NOTHING;
INSERT INTO genero (genero_id, genero_type) VALUES (2,'Femenino') ON CONFLICT (genero_id) DO NOTHING;
INSERT INTO genero (genero_id, genero_type) VALUES (1,'Masculino') ON CONFLICT (genero_id) DO NOTHING;