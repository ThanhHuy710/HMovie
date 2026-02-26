-- Script này sẽ chạy khi Postgres container khởi động lần đầu

-- Kiểm tra và tạo database cho Keycloak (nếu chưa có)
SELECT 'CREATE DATABASE "Hmovie_keycloak"'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'Hmovie_keycloak')\gexec
