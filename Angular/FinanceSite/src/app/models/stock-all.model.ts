import { NumberValueAccessor } from '@angular/forms';

export class StockAll{
    symbol:string;
   bidSize: number;
   bidPrice: number;
   askSize: number;
   askPrice: number;
   volume: number;
   lastSalePrice: number;
    lastSaleSize: number;
    lastSaleTime: number;
    lastUpdated:number;
    sector: string;
    securityType: string;
}