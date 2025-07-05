BIN=bin
MAIN=java -cp $(BIN) Main

.PHONY: build run criar-aluno criar-professor criar-servidor

build:
	javac -d bin **/*.java

run:
	$(MAIN)

criar-aluno:
	echo "2\n1\nMaria\nmaria@teste.com\n12345678\n1234\nEngenharia de Software\n2000\n10\n3" | $(MAIN)

criar-professor:
	echo "2\n2\nCarlos\ncarlos@teste.com\n98765432\nabcd\n123456\nMatemática\nProfessor\n3" | $(MAIN)

criar-servidor:
	echo "2\n3\nFernanda\nfernanda@teste.com\n99999999\npass\n654321\nAdministrador\nTI\n3" | $(MAIN)

login-aluno: #Após rodar criar-aluno
	echo "1\n2000\n1234\n7" | $(MAIN)

login-professor: #Após rodar criar-professor
	echo "1\n123456\nabcd\n7" | $(MAIN)

login-servidor: #Após rodar criar-servidor
	echo "1\n654321\npass\n7" | $(MAIN)

criar-espaço: #Após rodar criar-servidor
	echo "1\n654321\npass\n2\nsala1\n20\nuac\nlaboratório\n1\ncomputador\n7" | $(MAIN)

agendar-espaço: #Após rodar criar-espaço
	echo "1\n654321\npass\n3\nsala1\n2025-07-07 10:30\n2025-07-07 12:30\n7" | $(MAIN)

execao-login:
	echo "1\nteste\nteste\n3" | $(MAIN)

execao-data-fim-antes-inicio: #Após rodar criar-espaço
	echo "1\n654321\npass\n3\nsala1\n2025-04-04 10:30\n2025-03-03 12:30\n7" | $(MAIN)

execao-dias-excedidos: #Após rodar criar-aluno
	echo "1\n2000\n1234\n3\nsala1\n2025-04-04 10:30\n2025-04-05 12:30\n7" | $(MAIN)

execao-espaço-nao-encontrado: #Após rodar criar-espaço
	echo "1\n654321\npass\n3\nsala2\n7" | $(MAIN)

excecao-horario-indisponivel: #Após rodar criar-espaço
	echo "1\n654321\npass\n3\nsala1\n2025-07-07 10:30\n2025-07-07 12:30\n3\nsala1\n2025-07-07 11:30\n2025-07-07 12:00\n4\n7" | $(MAIN)

excecao-permissao-negada: #Após rodar criar-aluno
	echo "1\n2000\n1234\n2\n7" | $(MAIN)