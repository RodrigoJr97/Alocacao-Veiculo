import { Endereco } from "./Endereco";

export class Cliente {

  id: number;
  nome: string;
  cpf: string;
  numeroTelefone: number;
  email: string;
  endereco: Endereco;
  disponivel: boolean;
}
