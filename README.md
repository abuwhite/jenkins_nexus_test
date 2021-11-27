## Создать CI/CD используя Ansible, Jenkins, Docker и Nexus
Написать ansible playbook, при вызове которого будет подниматься docker контейнер с Jenkins, автоматически пулится любой проект из github.com (хоть «Hello world!»), собираться в артефакт и выкладываться в docker контейнер Nexus. Контейнеры можно обернуть в docker-compose.

Я как пользователь, после запуска playbook должен увидеть на своей машине по адресам:
- https://jenkins.example.com – саму сборку. Вход на Jenkins без авторизации.
- https://nexus.example.com – артефакт сборки из Jenkins. Вход в Nexus без авторизации.

Если будут какие-либо уточняющие вопросы – обращайся. Telegram: @bbbbmg

1. ~~Поднять Jenkis в docker~~ 
2. Стянуть проект с Github
3. Собрать проект в артефакт
4. Поднять Nexus с артефактами
5. Обернуть в compose
