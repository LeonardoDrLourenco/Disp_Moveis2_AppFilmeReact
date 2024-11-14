import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Alert, ActivityIndicator, FlatList, ScrollView, TouchableOpacity } from 'react-native';

const App = () => {
  const [filme, setFilme] = useState('');
  const [nomeComprador, setNomeComprador] = useState('');
  const [horario, setHorario] = useState('');
  const [dia, setDia] = useState('');
  const [preco, setPreco] = useState('');
  const [loading, setLoading] = useState(false);
  const [filmesCadastrados, setFilmesCadastrados] = useState([]);

  // Função para buscar os filmes cadastrados
  const buscarFilmes = async () => {
    setLoading(true);
    try {
      const resposta = await fetch('http://localhost:8080/filme/historicoCompras');
      if (resposta.ok) {
        const dados = await resposta.json();
        setFilmesCadastrados(dados);
      } else {
        Alert.alert('Erro', 'Falha ao carregar filmes cadastrados.');
      }
    } catch (erro) {
      Alert.alert('Erro', 'Não foi possível conectar à API.');
    } finally {
      setLoading(false);
    }
  };

  // Função para enviar o formulário
  const enviarFormulario = async () => {
    setLoading(true);
    try {
      const resposta = await fetch('http://localhost:8080/filme', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          filme: filme,
          nomeComprador: nomeComprador,
          horario: horario,
          dia: dia,
          preco: preco,
        }),
      });

      if (resposta.ok) {
        Alert.alert('Sucesso', 'Compra realizada com sucesso!');
        setFilme('');
        setNomeComprador('');
        setHorario('');
        setDia('');
        setPreco('');
        buscarFilmes();
      } else {
        Alert.alert('Erro', 'Falha ao cadastrar a compra.');
      }
    } catch (erro) {
      Alert.alert('Erro', 'Não foi possível conectar à API.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    buscarFilmes();
  }, []);

  return (
    <ScrollView contentContainerStyle={estilos.container}>
      <Text style={estilos.titulo}>Cadastro de Compra de Ingresso</Text>

      <View style={estilos.formulario}>
        <Text style={estilos.label}>Filme</Text>
        <TextInput
          style={estilos.input}
          value={filme}
          onChangeText={setFilme}
          placeholder="Nome do Filme"
          placeholderTextColor="#aaa" // Cor mais clara para o placeholder
        />
        
        <Text style={estilos.label}>Nome do Comprador</Text>
        <TextInput
          style={estilos.input}
          value={nomeComprador}
          onChangeText={setNomeComprador}
          placeholder="Nome do Comprador"
          placeholderTextColor="#aaa" // Cor mais clara para o placeholder
        />
        
        <Text style={estilos.label}>Horário</Text>
        <TextInput
          style={estilos.input}
          value={horario}
          onChangeText={setHorario}
          placeholder="Horário da Sessão"
          placeholderTextColor="#aaa" // Cor mais clara para o placeholder
        />
        
        <Text style={estilos.label}>Dia</Text>
        <TextInput
          style={estilos.input}
          value={dia}
          onChangeText={setDia}
          placeholder="Dia da Sessão"
          placeholderTextColor="#aaa" // Cor mais clara para o placeholder
        />
        
        <Text style={estilos.label}>Preço</Text>
        <TextInput
          style={estilos.input}
          value={preco}
          onChangeText={setPreco}
          placeholder="Preço do Ingresso"
          keyboardType="numeric"
          placeholderTextColor="#aaa" // Cor mais clara para o placeholder
        />

        {loading ? (
          <ActivityIndicator size="large" color="#007BFF" style={estilos.loading} />
        ) : (
          <View style={estilos.botaoContainer}>
            <TouchableOpacity style={estilos.botao} onPress={enviarFormulario}>
              <Text style={estilos.textoBotao}>Cadastrar Compra</Text>
            </TouchableOpacity>
          </View>
        )}
      </View>

      <Text style={estilos.tituloLista}>Filmes Cadastrados</Text>
      <FlatList
        data={filmesCadastrados}
        keyExtractor={(item, index) => index.toString()}
        renderItem={({ item }) => (
          <View style={estilos.itemLista}>
            <Text style={estilos.textoItem}>{`Filme: ${item.filme}`}</Text>
            <Text style={estilos.textoItem}>{`Comprador: ${item.nomeComprador}`}</Text>
            <Text style={estilos.textoItem}>{`Horário: ${item.horario}`}</Text>
            <Text style={estilos.textoItem}>{`Dia: ${item.dia}`}</Text>
            <Text style={estilos.textoItem}>{`Preço: R$ ${item.preco}`}</Text>
          </View>
        )}
      />
    </ScrollView>
  );
};

const estilos = {
  container: {
    padding: 20,
    backgroundColor: '#f5f5f5',
    fontFamily: "'Roboto', sans-serif",
    minHeight: '100vh', // Garante que a página ocupe toda a altura da tela
  },
  titulo: {
    fontSize: '28px',
    fontWeight: 'bold',
    marginBottom: '20px',
    textAlign: 'center',
    color: '#333',
    textTransform: 'uppercase', // Deixa o título mais impactante
  },
  tituloLista: {
    fontSize: '22px',
    fontWeight: 'bold',
    marginTop: '30px',
    marginBottom: '10px',
    color: '#333',
    textAlign: 'center',
  },
  formulario: {
    backgroundColor: '#ffffff',
    padding: '15px', // Diminuímos um pouco o padding
    borderRadius: '12px',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.15,
    shadowRadius: 4,
    elevation: 5, // Para efeito de sombra no Android
    marginBottom: '30px',
  },
  label: {
    fontSize: '16px',
    fontWeight: '500',
    marginBottom: '8px',
    color: '#333',
  },
  input: {
    width: '100%',
    padding: '08px', // Ajustei o padding para diminuir um pouco o tamanho do campo
    marginBottom: '15px',
    borderRadius: '8px',
    border: '1px solid #ddd',
    fontSize: '16px',
    backgroundColor: '#f9f9f9',
    boxSizing: 'border-box',
  },
  loading: {
    marginTop: '20px',
  },
  itemLista: {
    padding: '15px',
    marginBottom: '15px',
    backgroundColor: '#fff',
    borderRadius: '12px',
    boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
    width: '100%',
    marginHorizontal: '10px',
  },
  textoItem: {
    fontSize: '16px',
    marginBottom: '5px',
    color: '#555',
  },
  botaoContainer: {
    alignItems: 'center', // Centraliza o botão
    marginTop: '15px',
    width: '100%',
  },
  botao: {
    backgroundColor: '#007BFF',
    paddingVertical: '10px', // Reduzi o padding do botão
    paddingHorizontal: '20px', // Reduzi o padding do botão
    borderRadius: '30px', // Borda bem arredondada
    shadowColor: '#007BFF',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.25,
    shadowRadius: 6,
    elevation: 5,
    alignItems: 'center',
    justifyContent: 'center',
    width: '18%', // Largura reduzida do botão
  },
  textoBotao: {
    color: '#fff',
    fontSize: '16px', // Reduzi o tamanho da fonte
    fontWeight: 'bold',
    textTransform: 'uppercase',
  },
};

export default App;
