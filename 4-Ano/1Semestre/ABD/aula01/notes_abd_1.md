# ABD

Administração de Bases de Dados.

--- 

## Aulas 📕

- Aula 1

    Criar container

    ```bash
    docker run --user 501:20 -v ~/Desktop/1Semestre/ABD/aula01/data/:/var/lib/postgresql/data -e POSTGRES_DB=abd -e POSTGRES_USER=abduser -e POSTGRES_PASSWORD=segredo -p 5432:5432 -it -l pgsql postgres:12
    ```

    Listar todos os containers do Docker criados

    ```bash
    docker ps -a
    ```

    Commands de um container

    ```bash
    docker exec -it <process_name> pg_ctl status
    docker exec -it <process_name> pg_ctl reload
    docker exec -it <process_name> pg_ctl stop
    docker start -ai zealous_shockley
    ```

    Entrar no programa Docker

    ```bash
    docker exec -it <process_name> psql -U abduser abd
    ```

    Dentro do container

    ```bash
    \d
    \q
    ```

    Eliminar container

    ```bash
    docker rm <process_name>
    rm -rf data
    ```

---

## Embed links

[Slack](https://sdathome.slack.com/archives/C01CD8C464T)