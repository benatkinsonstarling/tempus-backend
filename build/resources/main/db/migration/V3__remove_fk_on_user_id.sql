-- V2__drop_user_foreign_key.sql
ALTER TABLE saved_locations
DROP CONSTRAINT saved_locations_user_id_fkey; -- This is likely the auto-generated name