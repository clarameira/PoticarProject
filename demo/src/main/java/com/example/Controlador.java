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
