import { Carro } from "../carros/Carros";
import { Cliente } from "../clientes/Clientes";

export class Contrato {
    id: any;
    carro: Carro;
    cliente: Cliente;
    valorTotal: number;
    dataInicio: Date;
    dataFinal: Date;
    statusContrato: boolean;
}
