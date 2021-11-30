run-playbook:
	ansible-playbook -i inventory.ini playbook.yaml

build:
	docker-compose build

up:
	docker-compose up