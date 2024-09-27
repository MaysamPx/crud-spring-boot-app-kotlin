DROP TABLE IF EXISTS public.items CASCADE;
CREATE TABLE public.items (
                              id uuid DEFAULT gen_random_uuid() NOT NULL,
                              "name" varchar NOT NULL,
                              price numeric NOT NULL,
                              "type" varchar NOT NULL,
                              CONSTRAINT items_pk PRIMARY KEY (id)
);