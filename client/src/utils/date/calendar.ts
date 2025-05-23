export function getYearFromNowMonths(): Date[] {
	const months = []
	const now = new Date()
	const end = new Date(now)
	end.setFullYear(now.getFullYear() + 1)

	const date = new Date(now)
	date.setDate(1)

	while (date < end) {
		months.push(new Date(date))
		date.setMonth(date.getMonth() + 1)
	}

	return months
}

export function getDaysForMonth(year: number, month: number): (Date | null)[] {
	const days = []
	const date = new Date(year, month, 1)
	const startDay = date.getDay()

	for (let i = 0; i < startDay; i++) {
		days.push(null)
	}

	while (date.getMonth() === month) {
		days.push(new Date(date))
		date.setDate(date.getDate() + 1)
	}

	return days
}

export function formatFlightDate(dateString: string, time: string, timeZone: string): string {
	const [year, month, day] = dateString.split('-')
	const [hour, minute] = time.split(':')
	const date = new Date(Date.UTC(+year, +month - 1, +day, +hour, +minute))

	const formatter = new Intl.DateTimeFormat('pt-BR', {
		timeZone,
		year: 'numeric',
		month: '2-digit',
		day: '2-digit',
		hour: '2-digit',
		minute: '2-digit',
		second: '2-digit',
		hour12: false,
	})
	const parts = formatter.formatToParts(date)
	const formatted = Object.fromEntries(parts.map(({ type, value }) => [type, value]))
	const tzDate = new Date(
		`${formatted.year}-${formatted.month}-${formatted.day}T${formatted.hour}:${formatted.minute}:${formatted.second}`
	)
	const offsetMinutes = -tzDate.getTimezoneOffset()
	const offsetHours = Math.floor(Math.abs(offsetMinutes) / 60)
	const offsetMins = Math.abs(offsetMinutes) % 60
	const sign = offsetMinutes >= 0 ? '+' : '-'

	const offset = `${sign}${String(offsetHours).padStart(2, '0')}:${String(offsetMins).padStart(2, '0')}`
	return `${tzDate.toISOString().slice(0, 19)}${offset}`
}

function correctDateFormatUTC(dateString: string): string {
	if (dateString.includes('Z') && /[+-]\d{2}:\d{2}$/.test(dateString)) {
		return dateString.replace('Z', '')
	}
	return dateString
}

export function formatFlightTime(dateString: string, originTimeZone: string): string {
	const fixedDate = correctDateFormatUTC(dateString)
	const date = new Date(fixedDate)
	const formatter = new Intl.DateTimeFormat('pt-BR', {
		timeZone: originTimeZone,
		hour: '2-digit',
		minute: '2-digit',
		hour12: false,
	})
	return formatter.format(date)
}
