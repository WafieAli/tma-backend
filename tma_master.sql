PGDMP                      |            postgres    16.2 (Debian 16.2-1.pgdg120+2)    16.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    5    postgres    DATABASE     s   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE postgres;
                postgres    false                        0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3359                        2615    16388 
   tma_master    SCHEMA        CREATE SCHEMA tma_master;
    DROP SCHEMA tma_master;
                postgres    false            !           0    0    SCHEMA tma_master    COMMENT     J   COMMENT ON SCHEMA tma_master IS 'Schema for Task Management Application';
                   postgres    false    6            �            1259    16390    tb_tasks    TABLE     9  CREATE TABLE tma_master.tb_tasks (
    task_id integer NOT NULL,
    title character varying(250),
    description character varying(250),
    status character varying(250),
    created_dtm timestamp with time zone DEFAULT now(),
    updated_dtm timestamp with time zone,
    "Completed" boolean DEFAULT false
);
     DROP TABLE tma_master.tb_tasks;
    
   tma_master         heap    postgres    false    6            �            1259    16389    tb_tasks_task_id_seq    SEQUENCE     �   CREATE SEQUENCE tma_master.tb_tasks_task_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE tma_master.tb_tasks_task_id_seq;
    
   tma_master          postgres    false    217    6            "           0    0    tb_tasks_task_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE tma_master.tb_tasks_task_id_seq OWNED BY tma_master.tb_tasks.task_id;
       
   tma_master          postgres    false    216            �           2604    16393    tb_tasks task_id    DEFAULT     |   ALTER TABLE ONLY tma_master.tb_tasks ALTER COLUMN task_id SET DEFAULT nextval('tma_master.tb_tasks_task_id_seq'::regclass);
 C   ALTER TABLE tma_master.tb_tasks ALTER COLUMN task_id DROP DEFAULT;
    
   tma_master          postgres    false    216    217    217                      0    16390    tb_tasks 
   TABLE DATA           r   COPY tma_master.tb_tasks (task_id, title, description, status, created_dtm, updated_dtm, "Completed") FROM stdin;
 
   tma_master          postgres    false    217   k       #           0    0    tb_tasks_task_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('tma_master.tb_tasks_task_id_seq', 9, true);
       
   tma_master          postgres    false    216            �           2606    16395    tb_tasks tb_tasks_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY tma_master.tb_tasks
    ADD CONSTRAINT tb_tasks_pkey PRIMARY KEY (task_id);
 D   ALTER TABLE ONLY tma_master.tb_tasks DROP CONSTRAINT tb_tasks_pkey;
    
   tma_master            postgres    false    217                 x�u�KO�0��ί�;��^ۉ�#E���V��%��R�uP�ҿO��}�G��hHL����؆�j[�x�jX�^�y�u���ކ� $��U
�z�zt2���Dh=�P&4He��e]���Zѳ��/餀��{e%���y�d��HOŬ��W|�wb��0��zJ�v�&�*���N,��B����4�4��757��:w϶�\���JK���Vbޫ�
��M��%T�g_E��p.���'qn�l���������D�c��JgN��dE�hŇt     