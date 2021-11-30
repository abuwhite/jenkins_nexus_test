run-playbook:
	ansible-playbook -i inventory.ini playbook.yaml

build:
	docker-compose build

start:
	docker-compose up