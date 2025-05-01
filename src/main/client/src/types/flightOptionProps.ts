export enum CabinClass {
    ECONOMY = 'ECONOMY',
    PREMIUM_ECONOMY = 'PREMIUM_ECONOMY',
    BUSINESS = 'BUSINESS',
    FIRST = 'FIRST'
}

interface PriceInfo {
    class: CabinClass;
    amount: number;
    currency: string;
}

interface FlightLeg {
    departureAirport: string;
    arrivalAirport: string;
    flightNumber: string;
    flightDuration: string;
    flightOperator: string;
    flightClass: CabinClass;
    departureDateUTC: Date;
    arrivalDateTimeUTC: Date;
    departureTimezone: string;
    arrivalTimezone: string;
}

export interface FlightOptionProps {
    departureAirport: string;
    arrivalAirport: string;
    flightTotalDuration: string;
    flightPrice: PriceInfo[];
    flightPriceCurrency: string;
    legs: FlightLeg[];
    departureDateUTC: Date;
    arrivalDateTimeUTC: Date;
    departureTimezone: string;
    arrivalTimezone: string;
}