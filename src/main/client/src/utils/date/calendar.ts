export function getYearFromNowMonths(): Date[] {
    const months = [];
    const now = new Date();
    const end = new Date(now);
    end.setFullYear(now.getFullYear() + 1);
  
    let date = new Date(now);
    date.setDate(1);
  
    while (date < end) {
      months.push(new Date(date));
      date.setMonth(date.getMonth() + 1);
    }
  
    return months;
  }
  
  export function getDaysForMonth(year: number, month: number): (Date | null)[] {
    const days = [];
    const date = new Date(year, month, 1);
    const startDay = date.getDay();
  
    for (let i = 0; i < startDay; i++) {
      days.push(null);
    }
  
    while (date.getMonth() === month) {
      days.push(new Date(date));
      date.setDate(date.getDate() + 1);
    }
  
    return days;
  }