export default function formatIntlToBrDate(value: string) {
	if (!value) return ''
	const [yyyy, mm, dd] = value.split('-')
	return `${dd}/${mm}/${yyyy}`
}
