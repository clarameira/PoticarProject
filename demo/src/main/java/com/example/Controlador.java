package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controlador {

    public void cadastrarCliente(Usuario novoCliente) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");
        ObjectMapper objectMapper = new ObjectMapper();

        // Lê os dados existentes do arquivo
        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        // Obtém a lista de clientes
        List<Map<String, Object>> listaClientes = (List<Map<String, Object>>) dadosMap.get("clientes");

        // Cria o novo cliente em um mapa
        Map<String, Object> novoClienteMap = objectMapper.convertValue(novoCliente, Map.class);

        // Inicializa a chave "locacoes" com uma lista vazia, se não existir
        if (!novoClienteMap.containsKey("locacoes") || novoClienteMap.get("locacoes") == null) {
            novoClienteMap.put("locacoes", new ArrayList<>());
        }

        // Adiciona o novo cliente à lista
        listaClientes.add(novoClienteMap);

        // Escreve os dados atualizados de volta ao arquivo
        String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);
        Files.writeString(caminhoArquivo, jsonAtualizado);
    }

    public Map<String, Object> lerDados(Path caminhoArquivo) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Verifica se o arquivo existe e tem conteúdo
        if (Files.exists(caminhoArquivo) && Files.size(caminhoArquivo) > 0) {
            // Lê o conteúdo JSON do arquivo
            String jsonConteudo = Files.readString(caminhoArquivo);

            // Converte o conteúdo JSON em um mapa
            Map<String, Object> dadosMap = objectMapper.readValue(jsonConteudo,
                    new TypeReference<Map<String, Object>>() {
                    });

            // Verifica se a chave "clientes" existe no mapa
            if (dadosMap.containsKey("clientes")) {
                // Obtém a lista de clientes
                List<Map<String, Object>> listaClientes = (List<Map<String, Object>>) dadosMap.get("clientes");

                // Itera sobre cada cliente na lista
                for (Map<String, Object> cliente : listaClientes) {
                    // Verifica se o campo "locacoes" está presente e não é nulo
                    if (!cliente.containsKey("locacoes") || cliente.get("locacoes") == null) {
                        // Adiciona uma lista vazia para "locacoes"
                        cliente.put("locacoes", new ArrayList<>());
                    }
                }
            }

            return dadosMap;
        }

        // Se o arquivo não existe ou está vazio, cria um novo mapa
        Map<String, Object> novoMapa = new HashMap<>();
        novoMapa.put("clientes", new ArrayList<>());
        novoMapa.put("carros", new ArrayList<>());

        return novoMapa;
    }

    public void listarClientes() throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaClientesMap = (List<Map<String, Object>>) dadosMap.get("clientes");

        if (listaClientesMap != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("Clientes:");

            for (Map<String, Object> clienteMap : listaClientesMap) {
                Usuario cliente = objectMapper.convertValue(clienteMap, Usuario.class);

                System.out.println(cliente);

                if (clienteMap.containsKey("locacoes")) {
                    List<Map<String, Object>> locacoesMap = (List<Map<String, Object>>) clienteMap.get("locacoes");

                    List<Locacao> locacoes = objectMapper.convertValue(locacoesMap,
                            TypeFactory.defaultInstance().constructCollectionType(List.class, Locacao.class));

                    System.out.println("Locações do cliente:");
                    for (Locacao locacao : locacoes) {
                        System.out.println(locacao);
                    }
                }
            }
        } else {
            System.out.println("Nenhum cliente encontrado.");
        }
    }

    public void verClientePorCpf(int cpfProcurado) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaClientesMap = (List<Map<String, Object>>) dadosMap.get("clientes");

        if (listaClientesMap != null) {
            ObjectMapper objectMapper = new ObjectMapper();

            for (Map<String, Object> clienteMap : listaClientesMap) {
                Usuario cliente = objectMapper.convertValue(clienteMap, Usuario.class);

                if (cliente.getCPF() == cpfProcurado) {
                    System.out.println("Cliente encontrado:");
                    System.out.println(cliente);

                    if (clienteMap.containsKey("locacoes")) {
                        List<Map<String, Object>> locacoesMap = (List<Map<String, Object>>) clienteMap.get("locacoes");

                        List<Locacao> locacoes = objectMapper.convertValue(locacoesMap,
                                TypeFactory.defaultInstance().constructCollectionType(List.class, Locacao.class));

                        System.out.println("Locações do cliente:");
                        for (Locacao locacao : locacoes) {
                            System.out.println(locacao);
                        }
                    }
                    return;
                }
            }

            System.out.println("Nenhum cliente encontrado com o CPF: " + cpfProcurado);
        } else {
            System.out.println("Nenhum cliente encontrado.");
        }
    }

    public void removerClientePorCpf(int cpfProcurado) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        if (dadosMap != null) {
            List<Map<String, Object>> listaClientesMap = (List<Map<String, Object>>) dadosMap.get("clientes");

            if (listaClientesMap != null) {
                boolean clienteEncontrado = false;

                for (int i = 0; i < listaClientesMap.size(); i++) {
                    Map<String, Object> clienteMap = listaClientesMap.get(i);

                    if (clienteMap != null && clienteMap.containsKey("cpf")) {
                        int cpfCliente = (int) clienteMap.get("cpf");

                        if (cpfCliente == cpfProcurado) {
                            listaClientesMap.remove(i);
                            clienteEncontrado = true;
                            break;
                        }
                    }
                }

                if (clienteEncontrado) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);
                    Files.writeString(caminhoArquivo, jsonAtualizado);
                    System.out.println("Cliente com CPF " + cpfProcurado + " removido com sucesso.");
                } else {
                    System.out.println("Nenhum cliente encontrado com o CPF: " + cpfProcurado);
                }
            } else {
                System.out.println("Nenhum cliente encontrado.");
            }
        } else {
            System.out.println("Erro ao carregar dados do arquivo.");
        }
    }

    public void editarClientePorCpf(int cpfProcurado, Usuario novosDadosCliente) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaClientesMap = (List<Map<String, Object>>) dadosMap.get("clientes");

        if (listaClientesMap != null) {
            boolean clienteEncontrado = false;

            for (int i = 0; i < listaClientesMap.size(); i++) {
                Map<String, Object> clienteMap = listaClientesMap.get(i);

                if (clienteMap != null && clienteMap.containsKey("cpf")) {
                    int cpfCliente = (int) clienteMap.get("cpf");

                    if (cpfCliente == cpfProcurado) {
                        clienteMap.put("nome", novosDadosCliente.getNome());
                        clienteMap.put("endereco", novosDadosCliente.getEndereco());
                        clienteMap.put("numero", novosDadosCliente.getNumero());
                        clienteMap.put("bairro", novosDadosCliente.getBairro());
                        clienteMap.put("telefone", novosDadosCliente.getTelefone());

                        clienteEncontrado = true;
                        break;
                    }
                }
            }

            if (clienteEncontrado) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);
                Files.writeString(caminhoArquivo, jsonAtualizado);
                System.out.println("Cliente com CPF " + cpfProcurado + " atualizado com sucesso.");
            } else {
                System.out.println("Nenhum cliente encontrado com o CPF: " + cpfProcurado);
            }
        } else {
            System.out.println("Nenhum cliente encontrado.");
        }
    }

    public void cadastrarLocacao(Locacao novaLocacao) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        if (dadosMap != null && dadosMap.containsKey("clientes")) {
            List<Map<String, Object>> listaClientesMap = (List<Map<String, Object>>) dadosMap.get("clientes");

            boolean clienteEncontrado = false;

            for (Map<String, Object> clienteMap : listaClientesMap) {
                if (clienteMap.containsKey("cpf")) {
                    Integer cpfCliente = (Integer) clienteMap.get("cpf");

                    if (cpfCliente != null && cpfCliente.equals(novaLocacao.getCpfLocador())) {
                        clienteEncontrado = true;

                        List<Map<String, Object>> locacoesMap;
                        if (clienteMap.containsKey("locacoes")) {
                            locacoesMap = (List<Map<String, Object>>) clienteMap.get("locacoes");
                        } else {
                            locacoesMap = new ArrayList<>();
                            clienteMap.put("locacoes", locacoesMap);
                        }

                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, Object> novaLocacaoMap = objectMapper.convertValue(novaLocacao, Map.class);

                        if (locacoesMap != null) {
                            locacoesMap.add(novaLocacaoMap);

                            break;
                        } else {
                            System.out.println("Erro: não foi possível acessar a lista de locações.");
                            return;
                        }
                    }
                }
            }

            if (clienteEncontrado) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);
                Files.writeString(caminhoArquivo, jsonAtualizado);

                System.out.println("Locação registrada com sucesso.");
            } else {
                System.out.println("Cliente com CPF " + novaLocacao.getCpfLocador() + " não encontrado.");
            }
        } else {
            System.out.println("Nenhum cliente encontrado.");
        }
    }

    public void editarLocacao(int cpfDono, int idLocacao, boolean novoDevolvido, boolean novoEntregue,
            boolean novoAprovado) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaClientesMap = (List<Map<String, Object>>) dadosMap.get("clientes");

        boolean clienteEncontrado = false;

        for (Map<String, Object> clienteMap : listaClientesMap) {
            if (clienteMap.containsKey("cpf")) {
                int cpfCliente = (int) clienteMap.get("cpf");

                if (cpfCliente == cpfDono) {
                    clienteEncontrado = true;

                    List<Map<String, Object>> locacoesMap = (List<Map<String, Object>>) clienteMap.get("locacoes");

                    for (Map<String, Object> locacaoMap : locacoesMap) {
                        if (locacaoMap.containsKey("idLocacao")) {
                            int idAtualLocacao = (int) locacaoMap.get("idLocacao");

                            if (idAtualLocacao == idLocacao) {
                                locacaoMap.put("devolvido", novoDevolvido);
                                locacaoMap.put("entregue", novoEntregue);
                                locacaoMap.put("aprovado", novoAprovado);

                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }

        if (clienteEncontrado) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);
            Files.writeString(caminhoArquivo, jsonAtualizado);

            System.out.println("Locação com ID " + idLocacao + " atualizada com sucesso.");
        } else {
            System.out.println("Cliente com CPF " + cpfDono + " não encontrado.");
        }
    }

    public void removerLocacao(int cpfLocador, int idLocacao) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaClientesMap = (List<Map<String, Object>>) dadosMap.get("clientes");

        boolean clienteEncontrado = false;

        for (Map<String, Object> clienteMap : listaClientesMap) {
            if (clienteMap.containsKey("cpf")) {
                int cpfCliente = (int) clienteMap.get("cpf");

                if (cpfCliente == cpfLocador) {
                    clienteEncontrado = true;

                    List<Map<String, Object>> locacoesMap = (List<Map<String, Object>>) clienteMap.get("locacoes");

                    boolean locacaoRemovida = false;
                    for (int i = 0; i < locacoesMap.size(); i++) {
                        Map<String, Object> locacaoMap = locacoesMap.get(i);

                        if (locacaoMap.containsKey("idLocacao")) {
                            int idAtualLocacao = (int) locacaoMap.get("idLocacao");

                            if (idAtualLocacao == idLocacao) {
                                locacoesMap.remove(i);
                                locacaoRemovida = true;
                                break;
                            }
                        }
                    }

                    if (locacaoRemovida) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);
                        Files.writeString(caminhoArquivo, jsonAtualizado);

                        System.out.println("Locação com ID " + idLocacao + " removida com sucesso.");
                    } else {
                        System.out.println("Locação com ID " + idLocacao + " não encontrada.");
                    }

                    break;
                }
            }
        }

        if (!clienteEncontrado) {
            System.out.println("Cliente com CPF " + cpfLocador + " não encontrado.");
        }
    }

    public void listarTodasAsLocacoes() throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaClientesMap = (List<Map<String, Object>>) dadosMap.get("clientes");

        if (listaClientesMap != null) {
            System.out.println("Lista de locações de todos os usuários:");

            for (Map<String, Object> clienteMap : listaClientesMap) {
                if (clienteMap.containsKey("locacoes")) {
                    List<Map<String, Object>> locacoesMap = (List<Map<String, Object>>) clienteMap.get("locacoes");

                    if (locacoesMap != null && !locacoesMap.isEmpty()) {
                        System.out.println(
                                "Cliente: " + clienteMap.get("nome") + " (CPF: " + clienteMap.get("cpf") + ")");

                        for (Map<String, Object> locacaoMap : locacoesMap) {
                            Locacao locacao = new ObjectMapper().convertValue(locacaoMap, Locacao.class);

                            System.out.println("  Locação ID: " + locacao.getIdLocacao());
                            System.out.println("    Data de Locação: " + locacao.getDataLoc());
                            System.out.println("    Data de Devolução: " + locacao.getDataDevolucao());
                            System.out.println("    Quilometragem: " + locacao.getQuilometragem());
                            System.out.println("    Valor da Locação: " + locacao.getValorLoc());
                            System.out.println("    Devolvido: " + locacao.isDevolvido());
                            System.out.println("    Entregue: " + locacao.isEntregue());
                            System.out.println("    Aprovado: " + locacao.isAprovado());
                        }
                        System.out.println();
                    }
                }
            }
        } else {
            System.out.println("Nenhum cliente encontrado.");
        }
    }

    public void verLocacao(int cpfLocador, int idLocacao) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaClientesMap = (List<Map<String, Object>>) dadosMap.get("clientes");

        if (listaClientesMap != null) {
            for (Map<String, Object> clienteMap : listaClientesMap) {
                if (clienteMap.containsKey("cpf")) {
                    int cpfCliente = (int) clienteMap.get("cpf");

                    if (cpfCliente == cpfLocador) {
                        if (clienteMap.containsKey("locacoes")) {
                            List<Map<String, Object>> locacoesMap = (List<Map<String, Object>>) clienteMap
                                    .get("locacoes");

                            for (Map<String, Object> locacaoMap : locacoesMap) {
                                Locacao locacao = new ObjectMapper().convertValue(locacaoMap, Locacao.class);

                                if (locacao.getIdLocacao() == idLocacao) {

                                    System.out.println("Detalhes da locação:");
                                    System.out.println("  Locação ID: " + locacao.getIdLocacao());
                                    System.out.println("  Data de Locação: " + locacao.getDataLoc());
                                    System.out.println("  Data de Devolução: " + locacao.getDataDevolucao());
                                    System.out.println("  Quilometragem: " + locacao.getQuilometragem());
                                    System.out.println("  Valor da Locação: " + locacao.getValorLoc());
                                    System.out.println("  Devolvido: " + locacao.isDevolvido());
                                    System.out.println("  Entregue: " + locacao.isEntregue());
                                    System.out.println("  Aprovado: " + locacao.isAprovado());
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("Locação com ID " + idLocacao + " não encontrada para o cliente com CPF " + cpfLocador);
        } else {
            System.out.println("Nenhum cliente encontrado.");
        }
    }

    public void cadastrarCarro(Carro novoCarro) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaCarrosMap;
        if (dadosMap.containsKey("carros")) {
            listaCarrosMap = (List<Map<String, Object>>) dadosMap.get("carros");
        } else {
            listaCarrosMap = new ArrayList<>();
            dadosMap.put("carros", listaCarrosMap);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> novoCarroMap = objectMapper.convertValue(novoCarro, Map.class);

        listaCarrosMap.add(novoCarroMap);

        String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);

        Files.writeString(caminhoArquivo, jsonAtualizado);

        System.out.println("Carro cadastrado com sucesso.");
    }

    public void removerCarroPorPlaca(String placa) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        if (dadosMap.containsKey("carros")) {
            List<Map<String, Object>> listaCarrosMap = (List<Map<String, Object>>) dadosMap.get("carros");

            boolean carroEncontrado = false;

            for (int i = 0; i < listaCarrosMap.size(); i++) {
                Map<String, Object> carroMap = listaCarrosMap.get(i);

                String placaCarro = (String) carroMap.get("placa");
                if (placaCarro != null && placaCarro.equals(placa)) {
                    listaCarrosMap.remove(i);
                    carroEncontrado = true;
                    break;
                }
            }

            if (carroEncontrado) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);
                Files.writeString(caminhoArquivo, jsonAtualizado);

                System.out.println("Carro com placa " + placa + " excluído com sucesso.");
            } else {
                System.out.println("Nenhum carro com a placa " + placa + " encontrado.");
            }
        } else {
            System.out.println("Nenhum carro cadastrado.");
        }
    }

    public void editarCarroPorPlaca(String placa, Carro carroEditado) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        if (dadosMap.containsKey("carros")) {
            List<Map<String, Object>> listaCarrosMap = (List<Map<String, Object>>) dadosMap.get("carros");

            boolean carroEncontrado = false;

            for (int i = 0; i < listaCarrosMap.size(); i++) {
                Map<String, Object> carroMap = listaCarrosMap.get(i);

                String placaCarro = (String) carroMap.get("placa");
                if (placaCarro != null && placaCarro.equals(placa)) {
                    carroMap.put("placa", carroEditado.getPlaca());
                    carroMap.put("cor", carroEditado.getCor());
                    carroMap.put("numPortas", carroEditado.getNumPortas());
                    carroMap.put("quilometragem", carroEditado.getQuilometragem());
                    carroMap.put("chassi", carroEditado.getChassi());
                    carroMap.put("valorLoc", carroEditado.getValorLoc());
                    carroMap.put("modelo", carroEditado.getModelo());
                    carroMap.put("disponivel", carroEditado.isDisponivel());

                    carroEncontrado = true;
                    break;
                }
            }

            if (carroEncontrado) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);
                Files.writeString(caminhoArquivo, jsonAtualizado);

                System.out.println("Carro com placa " + placa + " editado com sucesso.");
            } else {
                System.out.println("Nenhum carro com a placa " + placa + " encontrado.");
            }
        } else {
            System.out.println("Nenhum carro cadastrado.");
        }
    }

    public void listarCarros() throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        if (dadosMap.containsKey("carros")) {
            List<Map<String, Object>> listaCarrosMap = (List<Map<String, Object>>) dadosMap.get("carros");

            if (listaCarrosMap != null && !listaCarrosMap.isEmpty()) {
                System.out.println("Carros cadastrados:");
                for (Map<String, Object> carroMap : listaCarrosMap) {
                    System.out.println("Placa: " + carroMap.get("placa"));
                    System.out.println("Cor: " + carroMap.get("cor"));
                    System.out.println("Número de portas: " + carroMap.get("numPortas"));
                    System.out.println("Quilometragem: " + carroMap.get("quilometragem"));
                    System.out.println("Chassi: " + carroMap.get("chassi"));
                    System.out.println("Valor de locação: " + carroMap.get("valorLoc"));
                    System.out.println("Modelo: " + carroMap.get("modelo"));
                    System.out.println("Disponível: " + carroMap.get("disponivel"));
                    System.out.println();
                }
            } else {
                System.out.println("Nenhum carro cadastrado.");
            }
        } else {
            System.out.println("Nenhum carro cadastrado.");
        }
    }

    public void editarDisponibilidadeCarro(String placa, boolean novoDisponivel) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaCarrosMap = (List<Map<String, Object>>) dadosMap.get("carros");

        if (listaCarrosMap != null) {
            boolean carroEncontrado = false;

            for (Map<String, Object> carroMap : listaCarrosMap) {
                if (carroMap.containsKey("placa")) {
                    String placaCarro = (String) carroMap.get("placa");

                    if (placaCarro.equals(placa)) {
                        carroEncontrado = true;

                        carroMap.put("disponivel", novoDisponivel);

                        break;
                    }
                }
            }

            if (carroEncontrado) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonAtualizado = objectMapper.writeValueAsString(dadosMap);
                Files.writeString(caminhoArquivo, jsonAtualizado);

                System.out.println("Disponibilidade do carro com placa " + placa + " foi atualizada com sucesso.");
            } else {
                System.out.println("Carro com placa " + placa + " não encontrado.");
            }
        } else {
            System.out.println("Nenhum carro encontrado.");
        }
    }

    public void verCarroPorPlaca(String placa) throws IOException {
        Path caminhoArquivo = Paths.get("dados.json");

        Map<String, Object> dadosMap = lerDados(caminhoArquivo);

        List<Map<String, Object>> listaCarrosMap = (List<Map<String, Object>>) dadosMap.get("carros");

        if (listaCarrosMap != null) {
            for (Map<String, Object> carroMap : listaCarrosMap) {
                if (carroMap.containsKey("placa")) {
                    String placaCarro = (String) carroMap.get("placa");

                    if (placaCarro.equals(placa)) {
                        System.out.println("Carro encontrado:");
                        System.out.println("Placa: " + carroMap.get("placa"));
                        System.out.println("Cor: " + carroMap.get("cor"));
                        System.out.println("Número de portas: " + carroMap.get("numPortas"));
                        System.out.println("Quilometragem: " + carroMap.get("quilometragem"));
                        System.out.println("Chassi: " + carroMap.get("chassi"));
                        System.out.println("Valor da locação: " + carroMap.get("valorLoc"));
                        System.out.println("Modelo: " + carroMap.get("modelo"));
                        System.out.println("Disponível: " + carroMap.get("disponivel"));
                        return;
                    }
                }
            }

            System.out.println("Carro com placa " + placa + " não encontrado.");
        } else {
            System.out.println("Nenhum carro encontrado.");
        }
    }


}