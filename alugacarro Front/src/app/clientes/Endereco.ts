import { Cliente } from './Clientes';

export class Endereco {
  id: any;
  rua: string;
  numeroLogradouro: number;
  bairro: string;
  cidade: string;
  estado: string;
  complement: string;
  cliente:Cliente;
}
