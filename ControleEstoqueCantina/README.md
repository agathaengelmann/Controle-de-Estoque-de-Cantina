# Sistema de Controle de Estoque da Cantina

* **Aluna:** Ágatha Dias Engelmann
* **Turma:** 2DS
* **Disciplina:** Desenvolvimento de Software I
* **Tema Escolhido:** Opção A — Controle de Estoque da Cantina

## Descrição do Sistema
Sistema desenvolvido em Java para execução via console, criado para substituir o controle manual em caderno da cantina escolar. Permite cadastrar produtos com código único, listar itens, consultar saldos, registrar vendas com baixa automática no estoque e calcular o valor financeiro total armazenado.

## Funcionalidades Implementadas
1. **Cadastrar produto:** Valida código único, nome não vazio, preço maior que zero e estoque não negativo.
2. **Listar produtos:** Exibe todos os itens cadastrados com código, nome, preço e quantidade atual.
3. **Buscar produto:** Localiza e exibe os dados de um item específico pelo código.
4. **Registrar venda:** Realiza a baixa no estoque validando se há saldo disponível e se a quantidade vendida é válida.
5. **Exibir valor total do estoque:** Soma o produto (`preço unitário × quantidade`) de todos os itens cadastrados.
6. **Sair:** Encerra a execução do loop principal do menu.

## Como Executar o Programa
1. Clone este repositório ou baixe os arquivos `.java`.
2. Abra o projeto no **IntelliJ IDEA** (ou IDE Java de preferência, JDK 17+).
3. Execute o método `main` localizado no arquivo `src/Main.java`.
4. Interaja com o menu digitando o número correspondente à operação desejada no console.

## Registro dos Testes Obrigatórios

| # | Cenário de Teste | Ação Realizada e Dados Utilizados | Resultado Esperado | Resultado Obtido |
|---|---|---|---|---|
| 1 | **Cadastro válido** | Opção `1`: Código `10`, Nome `Suco`, Preço `5.00`, Qtd `10`. Depois Opção `2`. | Produto armazenado e exibido na listagem. | **Passou:** Mensagem de sucesso e item listado corretamente. |
| 2 | **Código repetido** | Opção `1`: Código `10` (já existente). | Sistema recusa o cadastro e alerta duplicidade. | **Passou:** Exibiu `[ERRO] Já existe um produto cadastrado com o código 10.` |
| 3 | **Informação inválida** | Opção `1`: Código `20`, Nome vazio `""` ou Preço `-5.00`. | Sistema bloqueia o cadastro e informa o erro. | **Passou:** Exibiu mensagem de erro e retornou ao menu sem salvar. |
| 4 | **Busca existente** | Opção `3`: Código `10`. | Exibe dados do produto `Suco`. | **Passou:** Exibiu `Código: 10 \| Produto: Suco \| Preço: R$ 5,00 \| Estoque: 10 un.` |
| 5 | **Busca inexistente** | Opção `3`: Código `999`. | Informa que o código não existe. | **Passou:** Exibiu `[AVISO] Nenhum produto encontrado com o código 999.` |
| 6 | **Operação principal** | Opção `4`: Vender `3` unidades do código `10`. Depois Opção `5`. | Estoque cai para `7` un. e valor total fica `R$ 35,00`. | **Passou:** Estoque atualizado para `7` e valor total exibido como `R$ 35,00`. |
| 7 | **Operação não permitida** | Opção `4`: Tentar vender `8` unidades do código `10` (saldo atual: 7). | Venda recusada e estoque mantido em `7`. | **Passou:** Exibiu `[ERRO] Estoque insuficiente!` e manteve `7` unidades. |
| 8 | **Lista vazia** | Antes de cadastrar itens, selecionar Opção `2` (Listar). | Sistema informa que não há registros. | **Passou:** Exibiu `Nenhum produto cadastrado no momento.` |
| 9 | **Opção inválida no menu** | Digitar `9` ou `abc` no menu principal. | Sistema informa erro e solicita nova escolha sem travar. | **Passou:** Exibiu erro e reexibiu o menu normalmente. |

## Limitações
* Os dados são armazenados apenas em memória (`ArrayList`) durante a execução do programa, sendo resetados ao encerrar o sistema.

## Uso de Ferramentas de Apoio
* Foi utilizada inteligência artificial (Gemini) como apoio para revisão da estrutura de classes, tratamento de exceções na leitura de dados com `Scanner` e organização da documentação de testes no `README.md`.