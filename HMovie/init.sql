-- Script này sẽ chạy khi Postgres container khởi động lần đầu

-- Kiểm tra và tạo database cho Keycloak (nếu chưa có)
SELECT 'CREATE DATABASE Hmovie_keycloak'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'Hmovie_keycloak')\gexec

-- Database HMovie sẽ được tạo tự động bởi biến môi trường POSTGRES_DB trong docker-compose
-- Nhưng nếu muốn chắc chắn, có thể để dòng này (sẽ báo lỗi nếu đã tồn tại, nhưng không ảnh hưởng nhiều)
-- CREATE DATABASE HMovie;
