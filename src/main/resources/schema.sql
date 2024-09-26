-- public.items definition

-- Drop table

-- DROP TABLE public.items;

CREATE TABLE public.items (
                              id uuid DEFAULT gen_random_uuid() NOT NULL,
                              "name" varchar NOT NULL,
                              price numeric NOT NULL,
                              "type" varchar NOT NULL,
                              CONSTRAINT items_pk PRIMARY KEY (id)
);


-- public.orders definition

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders (
                               id uuid DEFAULT gen_random_uuid() NULL,
                               itemid uuid NULL,
                               price numeric NULL,
                               amount numeric NOT NULL,
                               CONSTRAINT orders_item_fk FOREIGN KEY (id) REFERENCES public.items(id) ON DELETE CASCADE ON UPDATE CASCADE
);