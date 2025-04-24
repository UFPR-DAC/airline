import FlightOption from "../../components/FlightOption/FlightOption";
import FlightParams from "../../components/FlightParams/FlightParams";
import { CabinClass, FlightOptionProps } from "../../types/flightOptionProps";

export default function SearchResults() {

    const flight:FlightOptionProps = {
        departureAirport: 'YVR',
        arrivalAirport: 'NRT',
        flightTotalDuration: '14h20m',
        flightPrice: [
            {
                class: CabinClass.ECONOMY,
                amount: 1050,
                currency: 'USD'
            },
            {
                class: CabinClass.BUSINESS,
                amount: 2100,
                currency: 'USD'
            },
            {                  
                class: CabinClass.FIRST,  
                amount: 3400,
                currency: 'USD'
            }
        ],
        flightPriceCurrency: 'BRL',
        legs: [],
        departureDateUTC: new Date('2025-04-30T15:00:00Z'),
        arrivalDateTimeUTC: new Date('2025-04-30T17:50:00Z'),
        departureTimezone: 'America/Vancouver',
        arrivalTimezone: 'Asia/Tokyo'
    }
  return (
    <>
    <FlightParams />
    <FlightOption {...flight} />
    </>
  );
}
