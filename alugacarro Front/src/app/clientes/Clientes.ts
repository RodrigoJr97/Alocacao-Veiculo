import { Endereco } from "./Endereco";

export class Cliente {
  id?: any;
  nome: string;
  cpf: string;
  numeroTelefone: string;
  email: string;
  disponivel: boolean;
  endereco: Endereco;
}

/*
endereco?: {
    cep: string;
    logradouro: string;
    numeroLogradouro: string;
    bairro: string;
    localidade: string;
    uf: string;
    complemento: string;
  }

    endereco: Endereco = {
    cep: '',
    logradouro: '',
    numeroLogradouro: '',
    bairro: '',
    localidade: '',
    uf: '',
    complemento: '',
    ibge: '',
    gia: '',
    ddd: '',
    siafi: ''
  }

   cliente: Cliente = {
    nome: 'maria',
    cpf: '88950338092',
    numeroTelefone: '83 4001',
    email: 'maria@gmail.com',
    disponivel: true,
    endereco: {
      cep: '01311000',
      logradouro: 'Paulista',
      numeroLogradouro: '1609',
      bairro: 'Liberdade',
      localidade: 'Paulo',
      uf: 'sp',
      complemento: 'casa'
    }

     nome: 'Tulio',
    cpf: '56191257007',
    numeroTelefone: '3',
    email: 'tulioa@gmail.com',
    disponivel: true,
    endereco: {
      cep: '01311000',
      logradouro: 'Paulista',
      numeroLogradouro: '1609',
      bairro: 'Liberdade',
      localidade: 'Paulo',
      uf: 'sp',
      complemento: 'casa'
  }
*/
